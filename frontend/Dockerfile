FROM node:22
WORKDIR /opt/server
COPY *.json .
RUN npm install
COPY src /opt/server/src
COPY environments /opt/server/environments
EXPOSE 4200
CMD ["npm","start"]