package org.leplus.rhhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Demo application. */
@SpringBootApplication
public class RhhrApplication {

  /** The default constructor. */
  public RhhrApplication() {
    super();
  }

  /**
   * Main function.
   *
   * @param args the arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(RhhrApplication.class, args);
  }
}
