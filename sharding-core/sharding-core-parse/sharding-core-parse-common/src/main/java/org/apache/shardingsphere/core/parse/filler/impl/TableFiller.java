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

package org.apache.shardingsphere.core.parse.filler.impl;

import org.apache.shardingsphere.core.parse.exception.SQLParsingException;
import org.apache.shardingsphere.core.parse.filler.SQLSegmentFiller;
import org.apache.shardingsphere.core.parse.sql.context.Table;
import org.apache.shardingsphere.core.parse.sql.context.Tables;
import org.apache.shardingsphere.core.parse.sql.segment.common.SchemaSegment;
import org.apache.shardingsphere.core.parse.sql.segment.common.TableSegment;
import org.apache.shardingsphere.core.parse.sql.statement.SQLStatement;

/**
 * Table filler.
 *
 * @author duhongjun
 * @author zhangliang
 * @author panjuan
 */
public final class TableFiller implements SQLSegmentFiller<TableSegment> {
    
    @Override
    public void fill(final TableSegment sqlSegment, final SQLStatement sqlStatement) {
        sqlStatement.getTables().add(new Table(sqlSegment.getName(), sqlSegment.getAlias().orNull()));
        if (sqlSegment.getOwner().isPresent()) {
            if (containsMultipleSchemas(sqlStatement.getTables(), sqlSegment.getOwner().get())) {
                throw new SQLParsingException("Cannot support multiple schemas in one SQL");
            }
            sqlStatement.getTables().setSchema(sqlSegment.getOwner().get().getName());
        }
    }
    
    private boolean containsMultipleSchemas(final Tables tables, final SchemaSegment schemaSegment) {
        return schemaSegment.getName().equalsIgnoreCase(tables.getSchema().orNull());
    }
}
