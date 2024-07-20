import { Given, Then, When } from "@cucumber/cucumber";
import { chromium, Page, Browser, expect } from "@playwright/test";

let browser: Browser;
let page: Page;

Given('User navigates to the application', async function () {
    browser = await chromium.launch({ headless: false });
    page = await browser.newPage();
    await page.goto('http://localhost:3000');
});

Given('User click on the login link', async function () {
    // Locate the Login button
    const loginButton = page.locator('button', { hasText: 'Login' });

    // Click the Login button
    await loginButton.click();
});

Given('I am on the login page', async function () {
});

Given('I enter {string} into the username field', async function (string) {
    const usernameInput = page.locator('#username');
    await usernameInput.fill(string);
});

Given('I enter {string} into the password field', async function (string) {
    const passwordInput = page.locator('#password');
    await passwordInput.fill(string);
});

When('I click the login button', async function () {
    // login button type submit
    const loginButton = page.locator('button[type="submit"]', { hasText: 'Login' });
    await loginButton.click();
});

Then('I should see a {string} message', async function (string) {
    const successMessage = page.locator('#message');
    if (string === 'error')
        await expect(successMessage).toHaveText('Network Error');
});

Then('I should be redirected to the {string} page', async function (string) {
    await expect(page).toHaveURL('http://localhost:3000/' + string);

    // close browser
    await page.close();
    await browser.close();
});