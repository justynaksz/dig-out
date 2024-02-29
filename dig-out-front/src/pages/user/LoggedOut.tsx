import React, { useEffect } from 'react'
import { setAuthToken } from '../../helpers/setAuthToken.tsx'

export const LoggedOut = () => {

useEffect (() => {
    localStorage.removeItem("token")
    localStorage.removeItem("userId")
    setAuthToken(null)
    window.location.href = '/'
    }
)

    return (
        <>
        </>
    )
}
