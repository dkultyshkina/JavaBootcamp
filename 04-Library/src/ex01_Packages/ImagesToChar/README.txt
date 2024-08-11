
# удаление и создание папки таргет

rm -rf target && mkdir target

# скомпилировать файлы с расширением .java в классы в папке target

javac `find . -name "*.java"` -d ./target

# запуск файла app.java_.printer.Program, находящиеся в папке таргет, с параметрами

java -classpath ./target java_.printer.app.Program . 0 ../it.bmp