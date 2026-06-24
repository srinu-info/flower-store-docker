FROM node:22

WORKDIR /opt/server

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 4200

CMD ["npm", "start"]