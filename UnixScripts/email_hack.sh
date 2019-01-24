#!/bin/bash

web_site=$1
parse_urls.sh $web_site | xargs -n1 parse_emails.sh
