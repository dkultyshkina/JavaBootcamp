# удаление и создание папки таргет

rm -rf target && mkdir target && rm -rf lib && mkdir lib

# загрузка архивов библиотек JCommander и JCDP 

curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o lib/jcommander-1.82.jar
curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -o lib/JCDP-4.0.2.jar

# распаковка архивов библиотек

cd target
jar xf ../lib/jcommander-1.82.jar 
jar xf ../lib/JCDP-4.0.2.jar 
rm -rf META-INF 
cd ..

# скомпилировать файлы с расширением .java в классы в папке target

javac -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar: `find ./src -name "*.java"` -d ./target

# применяем к папке ресурсов команду переноса (cp)

cp -R src/resources ./target/.

# создаем исполняемый архив jar с текстовом файлом для редактирования манифеста с местом сохранения в папке target

jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C ./target .

# присваиваем права на исполнение архиву

chmod +x target/images-to-chars-printer.jar

# запуск архива images-to-chars-printer.jar, находящиеся в папке таргет, с требуемыми параметрами

java -jar ./target/images-to-chars-printer.jar --white=RED --black=GREEN