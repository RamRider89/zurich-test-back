# configuracion de postgresql y java en ubuntu 24.04

# UBUNTU SERVER: 192.168.100.24

# postgresql 16
sudo apt install postgresql

# configuracion del postgresql.conf y del hba_conf
# configuracion de usuarios postgres
sudo systemctl restart postgresql.service

# PGADMIN-WEB
curl -fsS https://www.pgadmin.org/static/packages_pgadmin_org.pub | sudo gpg --dearmor -o /usr/share/keyrings/packages-pgadmin-org.gpg
sudo sh -c 'echo "deb [signed-by=/usr/share/keyrings/packages-pgadmin-org.gpg] https://ftp.postgresql.org/pub/pgadmin/pgadmin4/apt/$(lsb_release -cs) pgadmin4 main" > /etc/apt/sources.list.d/pgadmin4.list && apt update'
sudo apt install pgadmin4-web
sudo /usr/pgadmin4/bin/setup-web.sh

# una vez realizados estos pasos tenemos acceso al servidor postgres en: 192.168.100.24/pgadmin


# UBUNTU BACKEND

# comprobando java
java --version

# maven
sudo apt-get install maven

# instalacion de sdk man
curl -s "https://get.sdkman.io" | bash
source "/home/idavid/.sdkman/bin/sdkman-init.sh"

# gradle
sdk install gradle 8.12

# springboot
sdk install springboot 

# siguiendo estos pasos tenemos las versiones:
# java: openjdk version "21.0.5" 2024-10-15
# gradle: 8.12
# maven: 3.8.7
# springboot: 3.4.1


# FRONT END LOCAL ubuntu 24.04

# NVM
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.1/install.sh | bash
nvm install node

# node
nvm install 22.13.0
nvm alias default 22.13.0 
node -v

# update npm
npm install npm@latest -g && npm --version

# angular
npm install -g @angular/cli && ng version

# versiones 
<!---
Angular CLI: 19.1.2
Node: 22.13.0
Package Manager: npm 11.0.0
OS: linux x64
-->
