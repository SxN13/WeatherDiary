package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class WeatherObject {
    public static HBox getWeatherBlock(int type){
        HBox hBox = new HBox();
        ImageView imageView = new ImageView(new Image("resources/icons/" + WeatherType.imageWeather[type]));
        Label label = new Label(WeatherType.textWeather[type]);

        imageView.setFitHeight(15);
        imageView.setFitWidth(15);

        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_LEFT);

        hBox.getChildren().add(imageView);
        hBox.getChildren().add(label);
        return hBox;
    }
}
