#!/bin/bash
PROGRAM_NAME="parse_emails"
VERSION="1.0"
DATE="1.1.2000"

case "$1" in 
	"--h") 
		#define styles
		bold=$(tput bold)
		normal=$(tput sgr0)
		
		echo "${bold}NAME${normal}";
		echo -e "\t${PROGRAM_NAME} - short decription your script place here."
		echo ""


		echo "${bold}DESCRIPTION${normal}"
		echo -e "\tLonger decription your script with technical detail how work it...";
		echo -e "\tLonger decription your script with technical detail how work it...";
		echo -e "\tLonger decription your script with technical detail how work it...";
		echo "";

		echo "${bold}USAGES${normal}";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} --h";
		echo -e "\t\t Display the manual page for this script.";
		echo "";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} --v";
		echo -e "\t\t Show information about version this script.";
		echo "";

		echo "${bold}AUTHOR${normal}";
		echo -e "\tOriginally written by Jiri Caga <jiricaga30@gmail.com>."
	;;
	"--v") 
		echo "Version $VERSION written by Jiri Caga $DATE";
	;;
	*)
		#Place for insert script code
		hellou(){
			echo "Hellou, $1";

		}

		hellou "world";
	;;
esac


