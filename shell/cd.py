import sys
import os


def chd(directory):
  if (directory=='..'):
            os.chdir('..')
            new=os.getcwd()
            print(new)
            print("HELLO")
  elif(directory=='~'):
            home=os.path.expanduser('~')
            os.chdir(home)
            new=os.getcwd()
            print(new)
  else:
        if os.path.isdir(directory):
            os.chdir(directory)
            new=os.getcwd()
            print(new)
            print("HEllo")
        else:
            print("directory does not exists")