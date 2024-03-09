import pygame
from pygame.locals import *

def display_image(image_path):
	pygame.init()

	infoObject = pygame.display.Info()
	screen = pygame.display.set_mode((infoObject.current_w, infoObject.current_h), FULLSCREEN)

	image = pygame.image.load(image_path)
	image = pygame.transform.scale(image, (infoObject.current_w, infoObject.current_h))

	screen.blit(image, (0,0))

	pygame.display.flip()

def display_close():
    pygame.quit()

# def display_image(image_path):	
	#import os
	#os.system('xdg-open {}'.format(image_path))
	#import pyautogui
	#pyautogui.press('f11')
	#pyautogui.press('f11')

	
