import React from 'react';

export type LocalizationType = {
    id: string;
    cemetery: string;
    quarter: string;
    localizationRow: string;
    localizationColumn: string;
}

export const Localization = ({
    id,
    cemetery,
    quarter,
    localizationRow,
    localizationColumn} : LocalizationType) => {
    return (
        <div>
            <div className='idLine'>Id: {id}</div>
            <table className='center'>
                <tbody>
                <tr className='itemLine'>
                    <td>Cemetery:</td><td>{cemetery}</td>
                </tr>
                <tr className='itemLine'>    
                    <td>Quarter:</td><td>{quarter}</td>
                </tr>
                <tr className='itemLine'>
                    <td>Row:</td><td>{localizationRow}</td>
                </tr>
                <tr className='itemLine'>
                    <td>Column:</td><td>{localizationColumn}</td>
                </tr>
                </tbody>
            </table>
        </div>
    )
}