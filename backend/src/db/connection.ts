import { drizzle, BetterSQLite3Database } from "drizzle-orm/better-sqlite3";
import { migrate } from "drizzle-orm/better-sqlite3/migrator";
import Database from "better-sqlite3";

/**
 * The Singleton class defines the `getInstance` method that lets clients access
 * the unique singleton instance.
 */
export class Singleton {
  private static db: BetterSQLite3Database;

  /**
   * The Singleton's constructor should always be private to prevent direct
   * construction calls with the `new` operator.
   */
  private constructor() {}

  /**
   * The static method that controls the access to the singleton instance.
   *
   * This implementation let you subclass the Singleton class while keeping
   * just one instance of each subclass around.
   */
  public static async getDB(): Promise<BetterSQLite3Database> {
    if (!Singleton.db) {
      const sqlite = new Database("sqlite.db");
      Singleton.db = drizzle(sqlite);
      await migrate(Singleton.db, { migrationsFolder: "drizzle" });
    }

    return Singleton.db;
  }
}
