import React from 'react';
import { OwnerType } from '../owner/Owner';

export type UserType = {
    id: string;
    nickname: string;
    password: string;
    email: string;
    role: string;
    graveOwner: OwnerType;
    photo: string;
}

export const User = ({
    id, 
    nickname,
    password, 
    email,
    role,
    graveOwner,
    photo} : UserType) => {
        return (
            <div>
                <div className='idLine'>Id: {id}</div>
                <table className='center'>
                    <tbody>
                        <tr className='itemLine'>
                            <td>Nickname: </td><td>{nickname}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Password: </td><td>{password}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Email: </td><td>{email}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Role: </td><td>{role}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Grave owner first name: </td><td>{graveOwner?.firstName}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Grave owner last name: </td><td>{graveOwner?.lastName}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Grave owner pesel: </td><td>{graveOwner?.pesel}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Photo: </td><td>{photo}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        )
    }