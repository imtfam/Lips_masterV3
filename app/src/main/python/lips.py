import numpy as np
import cv2
from  PIL import Image
import base64
import io
import face_recognition
from  PIL import  ImageColor

def creatorBox(img, points, scale = 2, masked = False, cropped = True):
    if masked:
        mask = np.zeros_like(img)
        mask = cv2.fillPoly(mask, [points], (255, 255, 255))
        #cv2.imshow('Mask', mask)
        img = cv2.bitwise_and(img, mask)
    if cropped:
        bbox = cv2.boundingRect(points)
        x, y, w, h = bbox
        imgCrop = img[y:y+h, x:x+w]
        imgCrop = cv2.resize(imgCrop, (0, 0), None ,scale, scale)
        return imgCrop
    else:
        return mask

def main(data,color):
    decoded_data = base64.b64decode(data)
    np_data = np.fromstring(decoded_data,np.uint8)
    img = cv2.imdecode(np_data,cv2.IMREAD_UNCHANGED)
    img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    #####

    face_landmarks_list = face_recognition.face_landmarks(img)
    if len(face_landmarks_list) == 0:
        return "null"


    #img = cv2.imread('image/1.jpg')
    img = cv2.resize(img, (0, 0), None, 1, 1)
    imgOriginal = np.copy(img)


    lips = []
    pointLandmarks = []

    for face_landmarks in face_landmarks_list:
        for i in range(len(face_landmarks['top_lip'])):
            lips.append((face_landmarks['top_lip'][i]))
        for i in range(len(face_landmarks['bottom_lip'])):
            lips.append((face_landmarks['bottom_lip'][i]))

    for i in range(len(lips)):
        x = lips[i][0]
        y = lips[i][1]
        pointLandmarks.append((x, y))

    pointLandmarks = np.array(pointLandmarks)
    print(pointLandmarks)

    imgLips = creatorBox(img, pointLandmarks, masked = True, cropped = False)

    maskImgLips = creatorBox(img, pointLandmarks, masked = True, cropped = False)
    _, maskImgLips = cv2.threshold(maskImgLips, thresh=180, maxval=255, type=cv2.THRESH_BINARY)

    redLips = np.copy(img)
    redLips[(maskImgLips == 255).all(-1)] = ImageColor.getcolor(color, "RGB")

    redLipsW = cv2.addWeighted(redLips, 0.3, img, 0.7, 0, redLips)

    #fig, ax = plt.subplots(1, 2, figsize = (12, 6))
    #ax[0].imshow(cv2.cvtColor(img, cv2.COLOR_BGR2RGB))
    #ax[1].imshow(cv2.cvtColor(redLipsW, cv2.COLOR_BGR2RGB))
    #cv2.imshow('a', redLipsW)

    pil_im = Image.fromarray(redLipsW)


    #####
    #convert image to Byte
    buff=io.BytesIO()
    pil_im.save(buff,format = "PNG")
    #conver it again to base64
    img_str = base64.b64encode(buff.getvalue())
    return ""+str(img_str,'utf-8')