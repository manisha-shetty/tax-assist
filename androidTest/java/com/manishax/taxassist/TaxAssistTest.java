package com.manishax.taxassist;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.manishax.taxassist.TaxAssistTest \
 * com.manishax.taxassist.tests/android.test.InstrumentationTestRunner
 */
public class TaxAssistTest extends ActivityInstrumentationTestCase2<TaxAssist> {

    public TaxAssistTest() {
        super("com.manishax.taxassist", TaxAssist.class);
    }

}
