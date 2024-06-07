import React, { ChangeEvent, FormEvent, useState } from 'react'
import api from '../../api/axiosConfig.js'
import { UserType } from '../../components/users/user/User.tsx'


export const RegistrationForm = () => {
    const [user, setUser] = useState<UserType>({} as UserType)

    const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setUser((prev) => ({
            ... prev,
            [name]: value
        }))
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        setUser(prev => ({
            ...prev,
            user: user
        }))

        addUser({
            ...user,
            id: '0'
        })
    }

    const addUser = async (newUser: UserType) => {
        try {
            // const createdUser = newUser
            // await api.post("/user", createdUser)
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <>
            <h4 className='title'> REGISTER </h4>
            <form className='body' onSubmit={handleSubmit}>
                <div>
                     <label htmlFor='nickanme'>
                        <input
                        type='text'
                        id='nickname'
                        name='nickname'
                        value={user?.nickname || ''}
                        onChange={handleInput}
                        placeholder='Nickname'
                        />
                    </label>
                </div>
               <div>
                    <label htmlFor='password'>
                        <input
                        type='password'
                        id='password'
                        name='password'
                        value={user?.password || ''}
                        onChange={handleInput}
                        placeholder='Password'
                        />
                    </label>
               </div>
                <div>
                    <label htmlFor='email'>
                        <input
                        type='text'
                        id='email'
                        name='email'
                        value={user?.email || ''}
                        onChange={handleInput}
                        placeholder='E-mail'
                        />
                    </label>
                </div>
            <button className='button' type='submit'>Register</button>
            </form>
        </>
    )
}