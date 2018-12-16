package org.redciudadana.monitorlegislativo

import org.junit.Assert.assertEquals
import org.junit.Test
import org.redciudadana.monitorlegislativo.screens.diputado.DiputadoPresenter

class DiputadoFragmentTest {
    @Test
    fun phoneRegexTest() {
        var phoneNumber: String? = "22323344"
        val presenter = DiputadoPresenter()
        var result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals("tel:22323344", result)

        phoneNumber = "12345678 / 12048238"
        result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals("tel:12345678", result)

        phoneNumber = ""
        result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals(null, result)

        phoneNumber = null
        result = presenter.getPhoneNumberUrl(phoneNumber)
        assertEquals(null, result)
    }
}