# Дневник погоды

## Что делает программа?
+ отобразить данные из базы данных;
+ записать данные;
+ изменить данные;
+ поиск вхождений по полям «время», значениям температур, наименованию осадков и примечанию.

### Структура таблицы
| Дата | Утро | День | Ветер | Погода | Примечание |
|------|------|------|-------|--------|------------|



### Системные требования
* Разрядность ОС - х64
* JDK 19+


## Описание столбцов таблицы

| Номер | Название столбца | Описание столбца |
|:-----:|:----------------:|:----------------:|
| 1 | Дата | Описывает дату, которая была выбрана при создании записи. |
| 2 | Утро (°C) | Температура утром |
| 3 | День (°C) | Температура днем |
| 4 | Ветер (м/с) | Скорость ветра |
| 5 | Осадки | Вариант осадок |
| 6 | Примечание | Комментарий к записи |




## Структура базы данных

| Поле 			| Тип 		| Описание			 |
|:-------------:|:---------:|:------------------:|
| id 			| int 		| Номер записи		 |
| time 			| long 		| Время записи		 |
| mTemp 		| string 	| Температура утром	 |
| dTemp 		| string	| Температура днем	 |
| windSpeed 	| string 	| Скорость ветра	 |
| weatherType 	| int 		| Код погоды		 |
| notes 		| String 	| Примечание		 |



# Кодирование погоды

| Код | Значок |Описание значка | Вид осадков |
|:-:|:-:|:-:|:-:|
| 0   || -  													| Нет данных Нет данных           |
| 1	  |![1](https://user-images.githubusercontent.com/34372985/223687373-e0b35178-0c3a-4357-86e2-1bcc71d6ac05.png) | Значок, символизирующий ясную погоду.                | Ясная погода            |
| 2	  |![2](https://user-images.githubusercontent.com/34372985/223687744-5807206f-4124-49ed-8e22-9c007c10c8a1.png) | Значок, символизирующий переменную облачность.       | Местами облачная погода |
| 3	  |![3](https://user-images.githubusercontent.com/34372985/223687763-306fc4f3-7cae-4fd4-8f62-0f163d47f2c9.png) | Значок, символизирующий пыль.                		  | Пыль                    |
| 4	  |![4](https://user-images.githubusercontent.com/34372985/223687776-d9426f21-ea3b-42ee-8669-95eae2041bdd.png) | Значок, символизирующий мглу.                		  | Мгла                    |
| 5	  |![5](https://user-images.githubusercontent.com/34372985/223687787-836ed179-024c-469f-9abe-b182dfead076.png) | Значок, символизирующий смог.                		  | Смог                    |
| 6	  |![6](https://user-images.githubusercontent.com/34372985/223687794-f5a674cb-41c9-49f6-8c18-9a8558038801.png) | Значок, символизирующий туман.                		  | Туман                   |
| 7	  |![7](https://user-images.githubusercontent.com/34372985/223687800-538b42f7-11bf-4b15-a1a2-dbff27ab8b9a.png) | Значок, символизирующий ветреную погоду.             | Ветреная погода         |
| 8	  |![8](https://user-images.githubusercontent.com/34372985/223687808-f5eea31c-3095-4d05-9be1-a9ae460cedd6.png) | Значок, символизирующий облачную погоду.             | Облачная погода         |
| 9	  |![9](https://user-images.githubusercontent.com/34372985/223688037-bdcb5530-7b7c-4e3c-91e2-552df19d866f.png) | Значок, символизирующий грозы.                		  | Грозы                   |
| 10  |![10](https://user-images.githubusercontent.com/34372985/223688049-412cd9e2-3c44-4387-b293-2c4c9a3ab350.png)| Значок, символизирующий дождь.                		  | Дождь                   |
| 11  |![11](https://user-images.githubusercontent.com/34372985/223688056-0b0cf471-a460-4089-ae6e-c3268c81cdfb.png)| Значок, символизирующий сильный дождь.               | Сильный дождь           |
| 12  |![12](https://user-images.githubusercontent.com/34372985/223688068-59cdccd0-3fca-441b-999b-ea6772cfdfba.png)| Значок, символизирующий моросящий дождь.             | Моросящий дождь         |
| 13  |![13](https://user-images.githubusercontent.com/34372985/223688083-e1055573-5c49-4bd2-a86d-82390ee6567b.png)| Значок, символизирующий град.                		  | Град                    |
| 14  |![14](https://user-images.githubusercontent.com/34372985/223688089-15cfeb43-f1f4-49c3-a9ec-5047d155d018.png)| Значок, символизирующий торнадо.                	  | Торнадо                 |
| 15  |![15](https://user-images.githubusercontent.com/34372985/223688098-86d74ff0-f3d0-48d3-a72e-8248aef1575f.png)| Значок, символизирующий снег.                		  | Снег                    |
| 16  |![16](https://user-images.githubusercontent.com/34372985/223688104-8783e4f4-0182-4a16-9402-9517766a6053.png)| Значок, символизирующий снег, который пойдет местами.| Местами снег            |
| 17  |![17](https://user-images.githubusercontent.com/34372985/223688115-67a7f57f-ca6b-4671-8abc-a533b8013508.png)| Значок, символизирующий метель.                	  | Метель                  |











