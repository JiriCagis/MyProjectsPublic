
user=$1;

mkdir github_projects_${user}
cd github_projects_${user}

wget -O- https://github.com/${user}?tab=repositories | 
	hxnormalize -x | 
	hxselect  -c "h3.repo-list-name" |
	grep -Eo 'href="[^\"]+"' | 
	sed s/'href="'/'https:\/\/github.com'/ |
	tee projects.txt |
	sed s/'"$'/'.git'/ |
	xargs -n1 git clone