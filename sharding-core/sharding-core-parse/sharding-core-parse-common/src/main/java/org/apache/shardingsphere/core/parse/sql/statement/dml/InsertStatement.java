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

package org.apache.shardingsphere.core.parse.sql.statement.dml;

import com.google.common.base.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shardingsphere.core.parse.sql.context.InsertValue;
import org.apache.shardingsphere.core.parse.sql.segment.dml.assignment.SetAssignmentsSegment;
import org.apache.shardingsphere.core.parse.sql.segment.dml.column.ColumnSegment;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Insert statement.
 *
 * @author zhangliang
 * @author maxiaoguang
 * @author panjuan
 */
@Getter
@Setter
@ToString(callSuper = true)
public final class InsertStatement extends DMLStatement {
    
    private final Collection<ColumnSegment> columns = new LinkedList<>();
    
    private final Collection<InsertValue> values = new LinkedList<>();
    
    private SetAssignmentsSegment setAssignment;
    
    /**
     * Get set assignment segment.
     * 
     * @return set assignment segment
     */
    public Optional<SetAssignmentsSegment> getSetAssignment() {
        return Optional.fromNullable(setAssignment);
    }
    
    /**
     * Judge is use default columns or not.
     * 
     * @return is use default columns or not
     */
    public boolean useDefaultColumns() {
        return columns.isEmpty() && null == setAssignment;
    }
    
    /**
     * Get value size.
     * 
     * @return value size
     */
    public int getValueSize() {
        if (!values.isEmpty()) {
            return values.iterator().next().getAssignments().size();
        }
        if (null != setAssignment) {
            return setAssignment.getAssignments().size();
        }
        return 0;
    }
}
