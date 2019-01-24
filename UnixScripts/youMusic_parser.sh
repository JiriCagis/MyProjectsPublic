#!/bin/bash
 
line=$1;
album=$(echo ${line} | cut -d ';' -f 1);
song=$(echo ${line} | cut -d ';' -f 2);
url=$(echo ${line} | cut -d ';' -f 3);

if [ -f "Music/$album/$song.mp3" ]
then
	echo "INFO: Exists song $album/$song in Music folder." | tee -a Music/log.txt;
else
	message=$(youtube-dl --output "Music/${album}/${song}.%(ext)s" \
					--no-playlist \
					--extract-audio \
					--audio-format mp3 \
					--audio-quality 192k \
					--prefer-ffmpeg ${url} 2>&1);
	
	if [[ $message == *"ERROR"* ]]
	then
		echo -e "ERROR: Not complete download $album/$song from URL:$url" | tee -a Music/log.txt;
		echo $message | tee -a Music/log.txt;
		echo "" | tee -a Music/log.txt;
	else
		echo -e "INFO: Complete download song: $album/$song." | tee -a Music/log.txt;
	fi
fi



