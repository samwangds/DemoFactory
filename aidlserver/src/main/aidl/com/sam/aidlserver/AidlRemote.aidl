// AidlRemote.aidl
package com.sam.aidlserver;

import com.sam.aidlserver.TestModel;

interface AidlRemote {
    int getCount(in List<TestModel> models);
}
