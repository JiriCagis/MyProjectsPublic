#!/bin/bash
PROGRAM_NAME="parse_urls"
VERSION="1.0"
DATE="7.8.2016"

case "$1" in 
	"--h") 
		#define styles
		bold=$(tput bold)
		normal=$(tput sgr0)
		
		echo "${bold}NAME${normal}";
		echo -e "\t${PROGRAM_NAME} - Script parse all URLs on web page."
		echo ""


		echo "${bold}DESCRIPTION${normal}"
		echo -e "\tProgram parse urls by regular expression and put into stdout.";
		echo -e "\tShort paths are replace complete URL to destination. For example:";
		echo -e "\tWhen you have web site \"www.books.com\" and short URL \"/favorite.html\",";
		echo -e "\tprogram supersede base url with comlete url and result is:";
		echo -e "\t\"www.books.com/favorie.html\""

		echo "";

		echo "${bold}USAGES${normal}";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} --h";
		echo -e "\t\t Display the manual page for this script.";
		echo "";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} --v";
		echo -e "\t\t Show information about version this script.";
		echo "";

		echo -e "\t${bold}${PROGRAM_NAME}${normal} www.example.com";
		echo -e "\t\t Get all URLs on the web site.";
		echo "";

		echo "${bold}AUTHOR${normal}";
		echo -e "\tOriginally written by Jiri Caga <jiricaga30@gmail.com>."
	;;
	"--v") 
		echo "Version $VERSION written by Jiri Caga $DATE";
	;;
	*)
		web_site=$1;
		base_domain=$(echo $web_site | 		#Get text representation URL
			     sed s/'http:\/\/'//  |		#Remove posible prefix http
			     sed s/'https:\/\/'// |		#Remove possible prefix https
				 grep -Eo '^[^\/]+') 		#Parse first domain in URL

		#Add www prefix if domain not contain
		if [[ $base_domain =~ www.* ]]
		then
			base_domain=$base_domain;
		else
			base_domain=$(echo www.$base_domain);
		fi

		#DOWNLOAD CONTENT OF PAGE AND PARSE URLS
		wget -qO- $web_site |				#Download web page content
		grep -Eoi '<a [^>]+>' |				#Parse only string <a ... >
		grep -Eo 'href="[^\"]+' |			#Parse only string href="..."
		sed s/'href="'// |					#Remove prefix href="
		grep -E '^(/|http|www)' |			#Remove all impurities which not are valid URLs
		sed -e 's/^\//'$base_domain'\//'	#Replace short path by full path
	;;
esac


