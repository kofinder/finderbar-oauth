# REQURIMENTS
- java 8
- mysql 5.6
- Maven

# MYSQL INSTALLATION
- apt update && apt install mysql-server
- mysql_secure_installation

# DOCKER
 - docker login registry.gitlab.com
 - docker pull registry.gitlab.com/finderbar/demo
 - docker run --name demo -p 80:8080 -d registry.gitlab.com/finderbar/demo
 - docker exec -it 5edaa2d4092c bash

# NGNIX CONFIG
- sudo service nginx stop
- sudo nano /etc/nginx/sites-available/oauthfinder
- sudo ln -s /etc/nginx/sites-available/example /etc/nginx/sites-enabled/
- sudo service nginx restart

# SWAP MEMORY
- sudo fallocate -l 1G /swapfile
- sudo dd if=/dev/zero of=/swapfile bs=1024 count=1048576
- sudo chmod 600 /swapfile
- sudo mkswap /swapfile
- sudo swapon /swapfile
- sudo nano /etc/fstab
- sudo swapon --show
- sysctl vm.swappiness=10 (if you want decrease swap)


# REMARK
  You can use the source freely; however, I don’t allow you to make money from it.
