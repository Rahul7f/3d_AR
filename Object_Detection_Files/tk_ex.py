from tkinter import *
from PIL import ImageTk,Image
import os
def dosome():
    os.system("python3  /home/pi/Desktop/cam_test.py ") 
def donoth():
    os.system('reboot')
def my():
    os.system("python3 /home/pi/open_term.py")
def obdet():
    os.system("python3 /home/pi/Desktop/Object_Detection_Files/object-ident-2.py")
   
master=Tk()
master.title("Project..")
image_0=Image.open('/home/pi/Desktop/pics_gui/AdobeStock_321871791_Preview.jpg')
back_end=ImageTk.PhotoImage(image_0)
master.geometry('720x480')
master.attributes('-fullscreen',True)
lbl=Label(master,image=back_end)
lbl.place(x=-85,y=-15)

master.bind("<Return>",dosome)
master.bind("<q>",donoth)
master.bind("<a>",my)
button_quit = Button(master, text="Exit program",bg='#021643',fg='white',font=("aerial",10,"italic"), command=master.destroy).place(x=610,y=390)
button_quit = Button(master, text="Start Camera",bg='#021643',fg='white',font=("aerial",10,"italic"), command=dosome).place(x=610,y=360)
button_quit = Button(master, text="My-First",bg='#021643',fg='white',font=("aerial",10,"italic"), command=my).place(x=610,y=330)
button_quit = Button(master, text="Object Detection",bg='#021643',fg='white',font=("aerial",10,"italic"), command=obdet).place(x=610,y=300)
button_quit = Button(master, text="Reboot",bg='#021643',fg='white',font=("aerial",10,"italic"), command=donoth).place(x=610,y=420)

master.mainloop()