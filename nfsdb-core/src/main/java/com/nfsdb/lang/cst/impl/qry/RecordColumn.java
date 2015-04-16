/*
 * Copyright (c) 2014-2015. Vlad Ilyushchenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nfsdb.lang.cst.impl.qry;


import com.nfsdb.storage.ColumnType;
import com.nfsdb.storage.SymbolTable;

public final class RecordColumn implements com.nfsdb.factory.configuration.RecordColumnMetadata {

    private final String name;
    private final ColumnType type;
    private final SymbolTable sTable;

    public RecordColumn(String name, ColumnType type, SymbolTable sTable) {
        this.name = name;
        this.type = type;
        this.sTable = sTable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SymbolTable getSymbolTable() {
        return sTable;
    }

    @Override
    public ColumnType getType() {
        return type;
    }
}
