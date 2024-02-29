import React, { useEffect } from 'react';
import { useState } from 'react';
import api from '../../../api/axiosConfig.js';
import { User, UserType } from '../user/User.tsx';

export const UserById = () => {
    const [userId, setUserId] = useState<string>()
    const [user, setUser] = useState<UserType>()

    const getUser = async () => {
        try {
            setUserId(localStorage.getItem("userId")!)
            console.log(userId)
            setUser((await api.get(`/auth/profile/${userId}`)).data)
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
        getUser()
    },);

    return (
        <div>
            <User {...user!}/>
        </div>
    )
}
