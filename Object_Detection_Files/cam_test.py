import numpy as np
import cv2
import RPi.GPIO as GPIO
import time

cap = cv2.VideoCapture(0)
cap.set(3,640) # set Width
cap.set(4,480) # set Height
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(21,GPIO.OUT, initial=GPIO.HIGH)
print("on")
GPIO.output(21,GPIO.LOW)
#time.sleep(100)
#print("off")
#GPIO.output(21,GPIO.LOW)

while(True):
    ret, frame = cap.read()
    frame = cv2.flip(frame, 1) # Flip camera vertically
    # gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    
    cv2.imshow('frame', frame)
    #cv2.imshow('gray', gray)
    
   # cv2.imshow('Preview', img)  # Display the Video
    if cv2.waitKey(20) & 0xFF == ord('q'):
        break
GPIO.output(21,GPIO.HIGH)

cap.release()
cv2.destroyAllWindows()