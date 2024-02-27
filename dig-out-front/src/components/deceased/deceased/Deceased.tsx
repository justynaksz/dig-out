import React from 'react';
import { GraveType } from '../../graves/grave/Grave';

export type DeceasedType = {
    id: string;
    firstName: string;
    lastName: string;
    birthDate: string;
    deathDate: string;
    infectiousDisease: boolean;
    grave: GraveType;
}

export const Deceased = ({
    id, 
    firstName,
    lastName, 
    birthDate,
    deathDate,
    infectiousDisease,
    grave} : DeceasedType) => {
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
                            <td>Birth date: </td><td>{birthDate}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Death date: </td><td>{deathDate}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Is infectious disease: </td><td>{infectiousDisease == undefined ? "undefined" : infectiousDisease.toString()}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Grave type: </td><td>{grave?.type}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Localization cemetery: </td><td>{grave?.localization?.cemetery}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Localization quarter: </td><td>{grave?.localization?.quarter}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Localization row: </td><td>{grave?.localization?.localizationRow}</td>
                        </tr>
                        <tr className='itemLine'> 
                            <td>Localization column: </td><td>{grave?.localization?.localizationColumn}</td>
                        </tr>
                        <tr className='itemLine'>
                            <td>Grave owner:</td><td>{grave?.graveOwner}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        )
    }
    