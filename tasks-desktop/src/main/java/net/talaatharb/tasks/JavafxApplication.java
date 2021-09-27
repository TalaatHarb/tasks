package net.talaatharb.tasks;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import net.talaatharb.tasks.uievents.StageReadyEvent;

@Slf4j
public class JavafxApplication extends Application {

	private ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception {
		ApplicationContextInitializer<GenericApplicationContext> initializer = genericApplicationContext -> {
			genericApplicationContext.registerBean(Application.class, () -> JavafxApplication.this);
			genericApplicationContext.registerBean(Parameters.class, this::getParameters);
			genericApplicationContext.registerBean(HostServices.class, this::getHostServices);
			log.info("Application context initializer configured");
		};

		this.context = new SpringApplicationBuilder().sources(BootifulFxApplication.class).initializers(initializer)
				.build().run(getParameters().getRaw().toArray(new String[0]));
		log.info("Application Context created");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.context.publishEvent(new StageReadyEvent(primaryStage));
		log.info("Stage ready event published");
	}

	@Override
	public void stop() throws Exception {
		log.info("Closing Application");
		this.context.close();
		Platform.exit();
	}

}
