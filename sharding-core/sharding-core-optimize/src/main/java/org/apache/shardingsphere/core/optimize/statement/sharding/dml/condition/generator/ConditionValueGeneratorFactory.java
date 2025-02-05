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

package org.apache.shardingsphere.core.optimize.statement.sharding.dml.condition.generator;

import com.google.common.base.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.core.optimize.statement.sharding.dml.condition.generator.impl.ConditionValueBetweenOperatorGenerator;
import org.apache.shardingsphere.core.optimize.statement.sharding.dml.condition.generator.impl.ConditionValueCompareOperatorGenerator;
import org.apache.shardingsphere.core.optimize.statement.sharding.dml.condition.generator.impl.ConditionValueInOperatorGenerator;
import org.apache.shardingsphere.core.parse.sql.context.Column;
import org.apache.shardingsphere.core.parse.sql.segment.dml.predicate.value.PredicateBetweenRightValue;
import org.apache.shardingsphere.core.parse.sql.segment.dml.predicate.value.PredicateCompareRightValue;
import org.apache.shardingsphere.core.parse.sql.segment.dml.predicate.value.PredicateInRightValue;
import org.apache.shardingsphere.core.parse.sql.segment.dml.predicate.value.PredicateRightValue;
import org.apache.shardingsphere.core.strategy.route.value.RouteValue;

import java.util.List;

/**
 * Condition value generator factory.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConditionValueGeneratorFactory {
    
    /**
     * Generate condition value.
     *
     * @param predicateRightValue predicate right value
     * @param column column
     * @param parameters SQL parameters
     * @return route value
     */
    public static Optional<RouteValue> generate(final PredicateRightValue predicateRightValue, final Column column, final List<Object> parameters) {
        if (predicateRightValue instanceof PredicateCompareRightValue) {
            return new ConditionValueCompareOperatorGenerator().generate((PredicateCompareRightValue) predicateRightValue, column, parameters);
        }
        if (predicateRightValue instanceof PredicateInRightValue) {
            return new ConditionValueInOperatorGenerator().generate((PredicateInRightValue) predicateRightValue, column, parameters);
        }
        if (predicateRightValue instanceof PredicateBetweenRightValue) {
            return new ConditionValueBetweenOperatorGenerator().generate((PredicateBetweenRightValue) predicateRightValue, column, parameters);
        }
        return Optional.absent();
    }
}
