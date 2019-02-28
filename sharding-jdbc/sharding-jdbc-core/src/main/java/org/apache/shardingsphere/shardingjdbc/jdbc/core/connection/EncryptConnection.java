/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.shardingjdbc.jdbc.core.connection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.shardingsphere.core.constant.DatabaseType;
import org.apache.shardingsphere.core.parsing.EncryptSQLParsingEngine;
import org.apache.shardingsphere.core.rule.EncryptRule;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.statement.EncryptPreparedStatement;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.statement.EncryptStatement;
import org.apache.shardingsphere.shardingjdbc.jdbc.unsupported.AbstractUnsupportedOperationConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * Encrypt connection.
 *
 * @author panjuan
 */
@Getter
@RequiredArgsConstructor
public final class EncryptConnection extends AbstractUnsupportedOperationConnection {

    private final Connection connection;

    private final EncryptRule encryptRule;

    private final DatabaseType databaseType;

    private final EncryptSQLParsingEngine encryptSQLParsingEngine;

    @Override
    @SneakyThrows
    public DatabaseMetaData getMetaData() {
        return connection.getMetaData();
    }

    @Override
    public Statement createStatement() {
        return new EncryptStatement(this);
    }

    @Override
    public Statement createStatement(final int resultSetType, final int resultSetConcurrency) {
        return new EncryptStatement(this, resultSetType, resultSetConcurrency);
    }

    @Override
    public Statement createStatement(final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) {
        return new EncryptStatement(this, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql) {
        return new EncryptPreparedStatement(this, sql);
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency) {
        return new EncryptPreparedStatement(this, sql, resultSetType, resultSetConcurrency);
    
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) {
        return new EncryptPreparedStatement(this, sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int autoGeneratedKeys) {
        return new EncryptPreparedStatement(this, sql, autoGeneratedKeys);
    
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final int[] columnIndexes) {
        return new EncryptPreparedStatement(this, sql, columnIndexes);
    
    }

    @Override
    public PreparedStatement prepareStatement(final String sql, final String[] columnNames) {
        return new EncryptPreparedStatement(this, sql, columnNames);
    
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    @SneakyThrows
    public void setAutoCommit(final boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    @SneakyThrows
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    @SneakyThrows
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    @SneakyThrows
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    @SneakyThrows
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    @Override
    @SneakyThrows
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    @Override
    public void setReadOnly(final boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    @Override
    public void setTransactionIsolation(final int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    @Override
    public SQLWarning getWarnings() {
        return null;
    }

    @Override
    public void clearWarnings() {
    }

    @Override
    public int getHoldability() {
        return ResultSet.CLOSE_CURSORS_AT_COMMIT;
    }

    @Override
    public void setHoldability(final int holdability) {
    }
}
