#!/bin/bash
PROGRAM_NAME="parse_emails"
VERSION="1.0"
DATE="7.8.2016"

case "$1" in 
	"--h") 
		#define styles
		bold=$(tput bold)
		normal=$(tput sgr0)
		
		echo "${bold}NAME${normal}";
		echo -e "\t${PROGRAM_NAME} - Script parse all emails on given web page."
		echo ""


		echo "${bold}DESCRIPTION${normal}"
		echo -e "\tProgram download web page and by regular expression go throught text";
		echo -e "\tfor check if contains any emails adress and put it on stdout.";
		echo "";

		echo "${bold}USAGES${normal}";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} --h";
		echo -e "\t\t Display the manual page for this script.";
		echo "";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} --v";
		echo -e "\t\t Show information about version this script.";
		echo "";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} www.example.com";
		echo -e "\t\t Download all emails adress from web site.";
		echo "";

		echo "${bold}AUTHOR${normal}";
		echo -e "\tOriginally written by Jiri Caga <jiricaga30@gmail.com>."
	;;
	"--v") 
		echo "Version $VERSION written by Jiri Caga $DATE";
	;;
	*)
		#download page content
		web_page=$(wget -qO- $1)

		#parse emails
		EMAIL_REGEX="\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}\b";
		echo $web_page | grep -E -o $EMAIL_REGEX | sort | uniq
	;;
esac


