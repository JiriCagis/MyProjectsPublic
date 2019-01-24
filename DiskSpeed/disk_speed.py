import sys
import os
import time

#------------------
# GLOBAL CONSTANTS
#------------------
APP_VERSION = 1.0
DEFAULT_SIZE_BLOCK = 1024*1024 #1MB
DEFAULT_COUNT_BLOCK = 50

# -----------------
# DEFINE FUNCTIONS
# -----------------
def generateBlockByte(size):
	block = ""
	count = 0
	while (count < size):
		block = block + "1" #present 1 byte
		count = count + 1
	return block

def saveBlock(block,file):
	file.write(block)

def showLogo():
	print "" + \
	" ______ ________________ _          _______ _______ _______ _______ ______ \n" + \
	"(  __  \__   __(  ____ | \    /\   (  ____ (  ____ (  ____ (  ____ (  __  \ \n" + \
	"| (  \  )  ) (  | (    \|  \  / /  | (    \| (    )| (    \| (    \| (  \  )\n" + \
	"| |   ) |  | |  | (_____|  (_/ /   | (_____| (____)| (__   | (__   | |   ) |\n" + \
	"| |   | |  | |  (_____  |   _ (    (_____  |  _____|  __)  |  __)  | |   | |\n" + \
	"| |   ) |  | |        ) |  ( \ \         ) | (     | (     | (     | |   ) |\n" + \
	"| (__/  ___) (__/\____) |  /  \ \  /\____) | )     | (____/| (____/| (__/  )\n" + \
	"(______/\_______\_______|_/    \/  \_______|/      (_______(_______(______/ " + str(APP_VERSION)
	print "DEVELOP BY: Jiri Caga (November 2015)\n"

# -----------------
# PREPARE INPUT ARGS
# -----------------
if len(sys.argv)>1:
	try:
		size_block = int(sys.argv[1])
		unit = sys.argv[2]
		cout_block = int(sys.argv[3])

		#convert insert block to bytes
		if unit == "B":
			pass
		if unit == "K":
			size_block*=1024
		if unit == "M":
			size_block*=1024*1024
		if unit == "G":
			size_block*=1024*1024*1024
	except:
		showLogo()
		print "Bad input arguments, it must be:\n\n" + \
		 	  " ->> python disk_speed [size block] [unit | K,M,G] [count block] \n\n" + \
		 	  "You can use standard without arguments(Default is " + str(DEFAULT_COUNT_BLOCK) + " block by " + str(DEFAULT_SIZE_BLOCK/1024/1024) + " MB)."
		sys.exit();
else:
	size_block = DEFAULT_SIZE_BLOCK
	cout_block = DEFAULT_COUNT_BLOCK

#---------------
# START PROGRAM
#---------------
showLogo()
block = generateBlockByte(size_block)
file = open("temp.bin","wb")

#Calculate disk speed
start_time = time.time()
for x in range(0,cout_block):
	saveBlock(block,file)
file.close()
end_time = time.time()
os.remove("temp.bin")

# Write result on console
totaly_time = end_time - start_time
totally_size = (size_block*cout_block)/1024/1024
avg = totally_size/totaly_time
print "		       ->> Disk speed is: :%.2f MB/s <<-" % avg

