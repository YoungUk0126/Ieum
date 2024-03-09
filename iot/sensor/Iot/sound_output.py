import pygame


pygame.mixer.init()
pygame.mixer.music.set_volume(1.0)

def soundPlay(ieumAnswer) :
    p = pygame.mixer.Sound(ieumAnswer)
    p.play()
    mixer = pygame.mixer
    return mixer

def soundStop(sound) :
    sound.stop()
