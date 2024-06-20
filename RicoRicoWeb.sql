DROP DATABASE IF EXISTS RicoRicoWeb;
CREATE DATABASE RicoRicoWeb;
USE RicoRicoWeb;

CREATE TABLE Recetas(
codReceta INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50),
minutosPreparacion INT,
dificultad ENUM('1','2','3','4','5'),
numComensales INT
);

INSERT INTO Recetas(nombre,minutosPreparacion,dificultad,numComensales) VALUES
	('Gazpacho',10,2,6) , ('Tortilla de patatas',40,3,4);

CREATE TABLE Ingredientes(
codIngrediente INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50),
descripcion TEXT
);

INSERT INTO Ingredientes(nombre,descripcion) VALUES
 ('Tomates','-'),('Pimientos verdes','-'),('Pimientos rojos','-'),('Cebollas','-'),('Huevos','-'),('Patatas','-'),('Agua','-'),
 ('Aceite de oliva','-'),('Vinagre','-'),('Sal','-'),('Ajo','-'),('Pepino','-');

CREATE TABLE IngredientesPorRecetas(
CodReceta INT,
CodIngrediente INT,
medida VARCHAR(25),
esOpcional BOOLEAN DEFAULT FALSE,
consideracion VARCHAR(255),
PRIMARY KEY(codReceta,codIngrediente),
FOREIGN KEY (codReceta) REFERENCES Recetas(codReceta),
FOREIGN KEY (codIngrediente) REFERENCES Ingredientes(codIngrediente)
);

INSERT INTO IngredientesPorRecetas(codReceta,CodIngrediente,medida,consideracion) VALUES
(1,1,'1 kg','Deben estar maduros'), (1,2,'1',NULL), (1,12,'1',NULL),(1,11,'2 dientes',NULL),(1,8,'50 ml',NULL),(1,7,'250 ml',NULL),
(1,10,'5 g',NULL),(1,9,'30 ml',NULL),
(2,6,'500 g',NULL),(2,5,'5',NULL),(2,8,NULL,NULL), (2,10,NULL,NULL);


CREATE TABLE PasosPreparacionRecetas(
codReceta INT,
numPaso INT,
descripcion TEXT,
esOpcional BOOLEAN,
PRIMARY KEY(codReceta,numPaso),
FOREIGN KEY (codReceta) REFERENCES Recetas(codReceta)
);

INSERT INTO PasosPreparacionRecetas(codReceta,numPaso,descripcion,esOpcional) VALUES
(1,1,'Para comenzar lavaremos bien los tomates y les quitamos el pedúnculo. Yo no los pelo, ya que los trituro todos y luego lo paso por un chino para quitar los restos',FALSE), 
(1,2,'Metemos todo en un vaso de batidora y trituramos bien. Como veis yo empecé con la batidora y luego me paso a una cazuela ya que se me quedó pequeña',FALSE), 
(1,3,'Aquí tenemos el cambio de cacerola, seguimos triturando los tomates junto los pepinos pelados, los pimientos, el ajo y el pan, yo pongo poco pan porque me gusta que salga muy suave',FALSE), 
(1,4,'Pasamos por un chino para quitar las pieles, pepitas o cualquier impureza que se haya quedado',FALSE), 
(1,5,'Una vez lo tenemos pasado ponemos un poco de aceite de oliva y de vinagre y en este paso es cuando vamos ajustando de sal, de vinagre y de comino. Es mejor ir poniéndolo poco a poco para que salga perfecto porque como nos pasemos la liamos',FALSE), 
(1,6,'Como veis queda un gazpacho muy suave y fino. Si vemos que el gazpacho queda muy espeso, podemos añadirle agua fría. A veces ocurre que al ponerle el pan, el aceite y vinagre de jerez se queda un poco espeso',FALSE), 
(1,7,'Poner a enfriar en la nevera',FALSE), 
(2,1,'Pelamos las patatas, las lavamos para quitar restos de suciedad y muy importante, las secamos',FALSE), 
(2,2,'Cortamos en láminas semifinas, a mí no me gusta que se deshagan sino que al freírlas se tuesten un poco. Las colocamos en un bol grande, donde luego vamos a mezclar con el huevo y añadimos sal al gusto. Removemos bien y reservamos',FALSE), 
(2,3,'Elegimos nuestra sartén más grande y antiadherente. La ponemos al fuego y añadimos un buen aceite de oliva virgen extra. No tengáis miedo en gastaros un poco de dinero en aceite, le va a dar ese punto de sabor que distingue vuestra tortilla de las demás, podéis emplear muchas variedades: arbequina, picual, cornicabra, hojiblanca, royal… el que más os guste, pero de calidad',FALSE), 
(2,4,'Introducimos las patatas cortadas y ya saladas y dejamos que se cocinen durante aproximadamente veinte minutos a fuego bajo. El tema del grosor de las patatas también va a gustos. Hay quien prefiere cortarlas a trozos muy pequeños, en láminas muy finas que casi se rompan al freír y o más bien grandes',FALSE), 
(2,5,'Mientras se están friendo las patatas, en el bol donde luego vamos a echar las patatas batimos los huevos, reservamos. Pelamos la cebolla y cortamos lo más fino posible',FALSE), 
(2,6,'En otra sartén calentamos aceite de oliva y añadimos los trozos de cebolla. Pochamos hasta que tenga un color dorado, que tenga un punto de caramelización pero sin llegar a quemarse. La cebolla se hará antes que las patatas, así que escurrimos y añadimos al bol con el huevo batido',FALSE), 
(2,7,'Quitamos con una espumadera de la sartén, dejando las patatas con el menor resto de aceite posible, bien escurridas. Si no queremos nada de aceite extra podemos emplear un colador grande. Las dejamos escurrir y luego las introducimos al bol con la cebolla y el huevo',FALSE), 
(2,8,'Reposamos la futura tortilla durante 15 minutos para que se junten bien todos los sabores. Pasados esos minutos esta mezcla ya está deliciosa, probad a tostar un poco de pan y añadidle una capita con esta mezcla, increíble',FALSE), 
(2,9,'En la misma sartén en la que hemos frito las patatas y una vez retirado el aceite. Cocinamos la mezcla que tenemos en reposo. A mí me gusta poco hecha, que al partirla con el tenedor salga un poco de huevo líquido. Para este tipo de tortilla sólo necesitamos 4 minutos a fuego medio-alto por cada lado. Depende de lo cuajada que queramos que quede la tortilla',FALSE), 
(2,10,'Para darle la vuelta yo empleo un plato llano grande que tengo para las ensaladas. Pero se puede usar una tapadera de borde liso, incluso ahora he visto que venden tapaderas especiales para dar la vuelta a la tortilla',FALSE), 
(2,11,'Emplead el método más cómodo y que más fácil os sea para que no se os desparrame, con cuidado. No desesperéis si no os sale, en ese caso tendréis una tortilla más cuajada, pero igual de rica',FALSE);


CREATE TABLE UsuariosRegistrados(
idUsuario VARCHAR(100) PRIMARY KEY,
password VARCHAR(128),
fechaAlta DATE,
estado ENUM('activo','baja')
);

INSERT INTO UsuariosRegistrados(idUsuario, password, fechaAlta, estado) VALUES
('fernando', '$4H3TY8ZaawFA','2020/07/06','activo'),
('ana', '$2dsf23TER?A','2021/03/04','activo');

CREATE TABLE ComentariosUsuarios(
codComentario INT PRIMARY KEY AUTO_INCREMENT,
codReceta INT,
idUsuario VARCHAR(100),
comentario TEXT,
FOREIGN KEY (codReceta) REFERENCES Recetas(codReceta),
FOREIGN KEY (idUsuario) REFERENCES UsuariosRegistrados(idUsuario)
);

INSERT INTO ComentariosUsuarios(codReceta,idUsuario,comentario) VALUES
(1,'fernando', 'Le pondría menos ajo del indicado'),
(2,'ana', 'No quiero crear una polémica, pero creo que la cebolla le sobra');

CREATE TABLE Imagenes(
codImagen INT PRIMARY KEY AUTO_INCREMENT,
ruta VARCHAR(255),
codReceta INT,
FOREIGN KEY (codReceta) REFERENCES Recetas(codReceta)
);


DELIMITER //
CREATE PROCEDURE Prueba()
BEGIN
	SELECT 'Esto es una prueba';
END//
DELIMITER ;


DELIMITER //
CREATE TRIGGER ControlFecha BEFORE INSERT ON UsuariosRegistrados
FOR EACH ROW
BEGIN
	IF NEW.fechaAlta IS NULL THEN 
	   SET NEW.fechaAlta=current_date();
	END IF;
END//
DELIMITER ;

