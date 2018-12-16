package org.redciudadana.monitorlegislativo

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.redciudadana.monitorlegislativo.screens.diputado.DiputadoFragment

class DiputadoFragmentTest {
    @Test
    fun phoneRegexTest() {
        var phoneNumber: String? = "22323344"
        val fragment = DiputadoFragment()
        var result = fragment.getPhoneNumberUrl(phoneNumber)
        assertEquals("tel:22323344", result)

        phoneNumber = "12345678 / 12048238"
        result = fragment.getPhoneNumberUrl(phoneNumber)
        assertEquals("tel:12345678", result)

        phoneNumber = ""
        result = fragment.getPhoneNumberUrl(phoneNumber)
        assertEquals(null, result)

        phoneNumber = null
        result = fragment.getPhoneNumberUrl(phoneNumber)
        assertEquals(null, result)
    }
}