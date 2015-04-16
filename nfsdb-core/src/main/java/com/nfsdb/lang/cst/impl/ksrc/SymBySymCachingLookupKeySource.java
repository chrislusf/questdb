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

package com.nfsdb.lang.cst.impl.ksrc;

import com.nfsdb.collections.IntIntHashMap;
import com.nfsdb.lang.cst.KeyCursor;
import com.nfsdb.lang.cst.KeySource;
import com.nfsdb.lang.cst.PartitionSlice;
import com.nfsdb.lang.cst.impl.virt.VirtualColumn;
import com.nfsdb.storage.SymbolTable;

public class SymBySymCachingLookupKeySource implements KeySource, KeyCursor {

    private final VirtualColumn masterKey;
    private final SymbolTable slave;
    private boolean hasNext = true;
    private IntIntHashMap map = new IntIntHashMap();

    public SymBySymCachingLookupKeySource(SymbolTable slave, VirtualColumn masterKey) {
        this.masterKey = masterKey;
        this.slave = slave;
    }

    @Override
    public KeyCursor cursor(PartitionSlice slice) {
        return this;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public int next() {
        hasNext = false;
        int m;
        int key = map.get(m = masterKey.getInt());
        if (key == -1) {
            map.put(m, key = slave.getQuick(masterKey.getSym()));
        }
        return key;
    }

    @Override
    public void reset() {
        hasNext = true;
    }

    @Override
    public int size() {
        return 1;
    }
}
