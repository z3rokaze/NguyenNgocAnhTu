// ========================================
// Locket Gold Premium - Enhanced Version
// ⚡ Performance: Fast & Smooth
// 🔐 Lifetime Premium Unlock
// 📅 Version: 1.5 (2026-01-15) - Enhanced
// 👤 Author: z3rokaze
// ========================================

(function () {
  'use strict';

  // ========= Constants (Updated) ========= //
  const PURCHASE_DATE = "2026-01-15T00:00:00Z";       // ✅ Current date
  const EXPIRES_DATE = "2099-12-31T23:59:59Z";        // ✅ Lifetime
  const PRODUCT_ID = "locket.premium.yearly";         // ✅ Stable product ID

  // ========= Mapping Configuration ========= //
  const APP_MAPPING = {
    '%E8%BD%A6%E7%A5%A8%E7%A5%A8': ['vip+watch_vip'],
    'Locket': ['Gold']
  };

  // ========= Get User-Agent (Optimized) ========= //
  const headers = $request.headers;
  const ua = headers["User-Agent"] || headers["user-agent"] || "";

  // ========= Parse Response Body (Enhanced Error Handling) ========= //
  let responseObj;
  try {
    responseObj = JSON.parse($response.body);

    // Ensure structure exists (Complete initialization)
    if (!responseObj.subscriber) {
      responseObj.subscriber = {};
    }
    if (!responseObj.subscriber.subscriptions) {
      responseObj.subscriber.subscriptions = {};
    }
    if (!responseObj.subscriber.entitlements) {
      responseObj.subscriber.entitlements = {};
    }
  } catch (error) {
    // Complete error recovery with full structure
    responseObj = {
      subscriber: {
        subscriptions: {},
        entitlements: {},
        original_app_user_id: "",
        original_application_version: ""
      }
    };
  }

  // ========= Premium Data (Pre-built for speed) ========= //
  const subscriptionData = {
    is_sandbox: false,
    ownership_type: "PURCHASED",
    billing_issues_detected_at: null,
    period_type: "normal",
    expires_date: EXPIRES_DATE,                    // ✅ Now consistent & longer
    grace_period_expires_date: null,
    unsubscribe_detected_at: null,
    original_purchase_date: PURCHASE_DATE,         // ✅ Updated date
    purchase_date: PURCHASE_DATE,                  // ✅ Updated date
    store: "app_store"
  };

  const entitlementData = {
    grace_period_expires_date: null,
    purchase_date: PURCHASE_DATE,                  // ✅ Updated date
    product_identifier: PRODUCT_ID,                // ⚠️ Keep original format
    expires_date: EXPIRES_DATE                     // ✅ Now same as subscription
  };

  // ========= Apply Mapping (Optimized Logic) ========= //
  let entitlementKey = "Gold";  // Default

  // Fast mapping lookup
  for (const key in APP_MAPPING) {
    if (ua.indexOf(key) !== -1) {
      entitlementKey = APP_MAPPING[key][0];
      break;
    }
  }

  // Apply premium data (Simplified logic)
  responseObj.subscriber.subscriptions[PRODUCT_ID] = subscriptionData;
  responseObj.subscriber.entitlements[entitlementKey] = entitlementData;

  // ========= Return Response (Fast stringify) ========= //
  $done({ body: JSON.stringify(responseObj) });

})();
