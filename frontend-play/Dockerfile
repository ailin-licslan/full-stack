# 构建镜像
## docker build -t="licslan" .

## docker run --name licslan -p 80:80 -d licslan

# docker exec -it af6 service nginx reload

# docker exec -it af6 /bin/sh


#基础镜像
FROM nginx:latest

# 作者
MAINTAINER LICSLAN

# dist文件中的内容复制到 ‘/user/share/nginx/html/’ 下面去
COPY dist/ /usr/share/nginx/html

# 本地配置的nginx 文件 覆盖 nginx镜像中默认的配置文件
COPY nginx-docker.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]


