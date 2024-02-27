package org.IDATG1005.grp3.db;

import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class DatabaseConnectionTest {

  private MockedStatic<DriverManager> mockedDriverManager;

  @BeforeEach
  void setUp() {
    // Mock DriverManager to prevent actual database connections
    mockedDriverManager = mockStatic(DriverManager.class);
  }

  @Test
  void testGetConnectionNotNull() throws Exception {
    // Setup
    Connection mockConnection = Mockito.mock(Connection.class);
    mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
        .thenReturn(mockConnection);

    // Execute & Verify
    assertNotNull(DatabaseConnection.getConnection(), "Connection should not be null");

    // Cleanup
    mockedDriverManager.close();
  }
}