## Алгоритм слияния сортированных файлов

Входные файлы содержат данные одного из двух видов: целые числа или строки. Данные
записаны в столбик (каждая строка файла – новый элемент). Строки могут содержать любые не
пробельные символы. Считается, что файлы предварительно отсортированы.

Результатом работы программы должен являться новый файл с объединенным содержимым
входных файлов, отсортированным по возрастанию или убыванию.

Если содержимое исходных файлов не позволяет произвести слияние (например,
нарушен порядок сортировки), производится частичная сортировка. Так, программа выведет название 
файла с нарушителем и позицию (номер сторки) элемента и закончит работать с этим файлом.

Параметры программы задаются при запуске через аргументы командной строки, по порядку:
1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;
2. тип данных (-s или -i), обязательный;
3. имя выходного файла, обязательное;
4. остальные параметры – имена входных файлов, не менее одного.

Примеры запуска из командной строки для Windows:
java -jar sort-it.jar -i -a out.txt in.txt (для целых чисел по возрастанию)
java -jar sort-it.jar -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)
java -jar sort-it.jar -d -s out.txt in1.txt in2.txt (для строк по убыванию)


Пример,

out.txt |      in1.txt  |    in2.txt  |   in3.txt  |   in4.txt
--------|---------------|-------------|------------|-----------
1       |        1      |      1      |      2     |     1
1       |        8      |      2      |     1      |     4
1       |        27     |      3      |     10     |     9
2       |               |             |            |  
2       |               |             |            |  
3       |               |             |            |  
4       |               |             |            |  
8       |               |             |            | 
9       |               |             |            | 
27      |               |             |            |  