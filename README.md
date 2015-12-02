# play-empty
Empty Playframework project with scala.js

###Как настроить чистый Play + Scala.js + shared code   
1) Создать папку PlayEmpty (поменять сюда текущую директорию, далее ~)   
2) Создать чистую инсталляцию  (activator new) (play-scala, название play)    
3) В idea импортировать проект ~/play, подправить файл build.sbt, чтобы settings прописывались явно для проекта play   
4) закрыть idea, удалить папку ~/play/.idea, переместить файл ~/play/build.sbt в ~/, подправить  project in file (“play”)  в build.sbt, 
скопировать ~/play/project/plugins.sbt и build.properties в ~/project   
5) импортировать проект ~ в idea   
Результат: play приложение является подпроектом   
    
6)Добавить scala js клиент проект + шаред код (https://github.com/vmunier/play-with-scalajs-example)   

