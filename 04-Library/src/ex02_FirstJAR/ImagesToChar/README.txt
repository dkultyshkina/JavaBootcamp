
# удаление и создание папки таргет

rm -rf target && mkdir target

# скомпилировать файлы с расширением .java в классы в папке target

javac `find . -name "*.java"` -d ./target

# применяем к папке ресурсов команду переноса (cp)

cp -R src/resources ./target/.

# создаем исполняемый архив jar с текстовом файлом для редактирования манифеста с местом сохранения в папке target

jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C ./target .

# присваиваем права на исполнение архиву

chmod +x target/images-to-chars-printer.jar

# запуск архива images-to-chars-printer.jar, находящиеся в папке таргет, с требуемыми параметрами

java -jar ./target/images-to-chars-printer.jar . 0 
