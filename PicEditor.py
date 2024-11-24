from PIL import Image

#Open an image from a file
img = Image.open('Painting.jpg')

pixels = img.load()

width, height = img.size

#Iterate + RGB values
for row in range(height // 2):
    for col in range(width):
        r, g, b = img.getpixel((col, row))
        pixels[col, row + height // 2] = (r, g, b)
    
#Save 
duplicated_painting = "duplicated_" + 'Painting.jpg'
img.save(duplicated_painting)
     

                