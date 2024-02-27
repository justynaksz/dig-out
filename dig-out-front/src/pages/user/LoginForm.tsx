import React from 'react'
import { FormEvent, useState } from 'react'


export const LoginForm = () => {

    return (
        <>
            <h4 className='title'> LOGIN </h4>
            <form className='body'>
                <div>
                   <label htmlFor='nickanme'>
                        <input
                        className='input'
                        type='text'
                        id='nickname'
                        name='nickname'
                        // value={user?.nickname || ''}
                        // onChange={handleInput}
                        placeholder='Nickname'
                        />
                    </label> 
                </div>
                <div>
                    <label htmlFor='password'>
                        <input
                        className='input'
                        type='text'
                        id='password'
                        name='password'
                        // value={user?.password || ''}
                        // onChange={handleInput}
                        placeholder='Password'
                        />
                    </label> 
                </div>
                <button className='button' type='submit'>Login</button>
            </form>
            </>
    )
}