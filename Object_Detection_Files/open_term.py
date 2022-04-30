import os
#import RPi.GPIO as GPIO
#import time
import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(21,GPIO.OUT, initial=GPIO.HIGH)
print("on")
GPIO.output(21,GPIO.LOW)
os.system("/usr/bin/lxterminal -e 'my-first'")
#time.sleep(1)
GPIO.output(21,GPIO.HIGH)