package com.timocom.bs.test.dbserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.h2.tools.Server;

/**
 *
 * @author t2g8
 */
public class Main {

  protected static final String CREATE_TABLESSQL = "/create_tables.sql";
  protected static final String INSERT_DATASQL = "/insert_data.sql";

  public static void main(String[] args) throws IOException, SQLException {

    final Path dbPath = Paths.get("", "qualitybase").toAbsolutePath().normalize();
    System.out.println("Starte von " + dbPath);
    if (!Files.exists(dbPath)) {
      Files.createDirectory(dbPath);
    }

    initDB(dbPath);

    Server server = initServer();

    initCommandline();

    System.out.print("Schalte Server ab ..........................................");
    server.stop();
    System.out.println("OK.");
    System.out.println("Bye.");
  }

  protected static void initCommandline() throws SQLException, IOException {
    System.out.println("Bereit. Gib 'quit' zum Beenden oder 'reset' zum Zurücksetzen der Daten ein.\n");
    Scanner scanner = new Scanner(System.in);
    String line;
    while (!(line = scanner.nextLine()).equals("quit")) {
      if (line.equals("reset")) {
        try (
                Connection connectionH2 = DriverManager.getConnection("jdbc:h2:tcp://localhost/qualitybase", "sa", "");
                Statement statementH2DB = connectionH2.createStatement();) {

          statementH2DB.execute(loadScript(INSERT_DATASQL));
          System.out.println("Daten zurückgesetzt, OK.");
        }
      }
    }
  }

  protected static Server initServer() throws SQLException {
    System.out.print("Starte lokalen Datenbank-Server ........................... ");
    final Server server = Server.createTcpServer("-baseDir", Paths.get("").toAbsolutePath().normalize().toString() + "/qualitybase"
    ).start();
    System.out.println("OK.");
    System.out.println("Server-URL: jdbc:h2:tcp://localhost/qualitybase, user=sa ");
    return server;
  }

  protected static void initDB(final Path dbPath) throws SQLException, IOException {
    System.out.println("Richte Datenbank ein ......................................");
    try (
            Connection connectionH2 = DriverManager.getConnection("jdbc:h2:file:" + dbPath.toString() + "/qualitybase", "sa", "");
            Statement statementH2DB = connectionH2.createStatement();) {
      statementH2DB.execute(loadScript(CREATE_TABLESSQL));
      statementH2DB.execute(loadScript(INSERT_DATASQL));
    }
  }

  protected static String loadScript(String path) throws IOException {
    final InputStream inputStream = Main.class.getResourceAsStream(path);
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
    StringBuilder sb = new StringBuilder();
    for (String line; (line = br.readLine()) != null;) {
      sb.append(line).append("\n");
    }
    return sb.toString();
  }

}
