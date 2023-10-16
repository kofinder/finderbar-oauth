# Requirement
- 1). java 8
- 2). mysql 5.6
- 3). Maven

# MYSQL INSTALLATION
- apt update && apt install mysql-server
- mysql_secure_installation

# DOCKER
 - docker login registry.gitlab.com
 - docker pull registry.gitlab.com/finderbar/demo
 - docker run --name demo -p 80:8080 -d registry.gitlab.com/finderbar/demo
 - docker exec -it 5edaa2d4092c bash

#NGNIX
- sudo service nginx stop
- sudo nano /etc/nginx/sites-available/oauthfinder
- sudo ln -s /etc/nginx/sites-available/example /etc/nginx/sites-enabled/
- sudo service nginx restart

#SWAP
- sudo fallocate -l 1G /swapfile
- sudo dd if=/dev/zero of=/swapfile bs=1024 count=1048576
- sudo chmod 600 /swapfile
- sudo mkswap /swapfile
- sudo swapon /swapfile
- sudo nano /etc/fstab
- sudo swapon --show
- sysctl vm.swappiness=10 (if you want decrease swap)


# NOTE
    - You can take the soure freely. However, I  don't allow to you to make money on it.
