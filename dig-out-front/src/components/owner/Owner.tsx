import React from 'react';

export type OwnerType = {
    id: string;
    firstName: string;
    lastName: string;
    pesel: string;
    street: string;
    parcel: string;
    city: string;
    postalCode: string;
    country: string;
    phoneNumber: string;
}

export const Owner = ({
    id, 
    firstName,
    lastName, 
    pesel,
    street,
    parcel,
    city,
    postalCode,
    country,
    phoneNumber} : OwnerType) => {
        return (
            <div>
                <div className='idLine'>Id: {id}</div>
                <table className='center'>
                    <tbody>
                        <tr className='itemLine'>
                            <td>First name: </td><td>{firstName}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Last name: </td><td>{lastName}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Pesel: </td><td>{pesel}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Street: </td><td>{street}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Parcel: </td><td>{parcel}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>City: </td><td>{city}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Postal code: </td><td>{postalCode}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Country: </td><td>{country}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>PhoneNumber: </td><td>{phoneNumber}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        )
    }